(defproject lein-project-clean "0.1.2"
  :description "A lein plugin to tidy/refactor source files in a project via refactor-nrepl middleware"
  :url "https://github.com/joelittlejohn/lein-project-clean"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[cider/cider-nrepl "0.14.0"]
                 [commons-io "2.5"]
                 [refactor-nrepl "2.2.0"]
                 [rewrite-clj "0.6.0"]]
  :deploy-repositories {"releases" :clojars}
  :eval-in-leiningen true)
