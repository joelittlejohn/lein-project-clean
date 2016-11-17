# lein-project-clean

A lein plugin to tidy/refactor source files in a project via refactor-nrepl middleware. This plugin uses refactor-nrepl's library of refactorings and Yannick Scherer's excellent [rewrite-clj](https://github.com/xsc/rewrite-clj).

## Usage

Put 

![](https://clojars.org/lein-project-clean/latest-version.svg) 

into the `:plugins` vector of your `:user` profile.

In a project you want to clean:

    $ lein project-clean

## Project clean-up tasks that this plugin runs

* Clean ns declarations in all Clojure files in source-paths and test-paths (sort, standardise and remove redundant 'requires', see [refactor-nrepl's clean-ns](https://github.com/clojure-emacs/refactor-nrepl/blob/a9d5dcf20e9657fa8afd7ffd609ff6c284ad893a/src/refactor_nrepl/ns/clean_ns.clj))

## Todo

* Allow config options to be supplied, e.g. whether to use the prefix form
* Add a 'dry run' mode that doesn't rewrite files
* Add more clean-up tasks

## License

Copyright © 2016 Joe Littlejohn

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
