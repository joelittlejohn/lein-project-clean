(ns leiningen.project-clean
  (:require [clojure.java.io :as io]
            [refactor-nrepl.core :refer [source-file?]]
            [refactor-nrepl.ns
             [clean-ns :refer [clean-ns]]
             [pprint :refer [pprint-ns]]]
            [rewrite-clj
             [parser :as parser]
             [zip :as zip]])
  (:import org.apache.commons.io.FileUtils))

(defn- clj-files
  "Get a seq of all .clj files found in the given directory"
  [dir]
  (-> (FileUtils/iterateFiles dir (into-array ["clj"]) true)
      iterator-seq
      sort))

(defn- replace-ns
  "Replace the namespace form in the given file with a new
  namepace (given as a formatted string)."
  [file new-ns-string]
  (let [new-ns-sexp (parser/parse-string new-ns-string)]
    (-> (slurp file)
        zip/of-string
        (zip/find-value zip/next 'ns)
        zip/up
        (zip/replace new-ns-sexp)
        zip/root-string
        (->> (spit file)))))

(defn- dirs
  "Get a seq of all the paths in the project that exist and are expected
  to contain Clojure source files."
  [project]
  (for [path (concat (:source-paths project) (:test-paths project))
        :let [dir (io/file path)]
        :when (.exists dir)]
    dir))

(defn project-clean
  "Clean the current project and all Clojure files within it."
  [project & args]
  (with-redefs [refactor-nrepl.ns.prune-dependencies/libspec-in-use-with-refer-all? (constantly true)]
    (doseq [dir (dirs project)
            file (clj-files dir)
            :let [path (.getPath file)]]
      (if (source-file? path)
        (if-let [new-ns (some-> (clean-ns {:path path}) pprint-ns)]
          (do (println "project-clean: Rewriting cleaned" path)
              (replace-ns file new-ns))
          (println "project-clean: No clean required in" path))
        (println "project-clean: Ignoring" path)))))
