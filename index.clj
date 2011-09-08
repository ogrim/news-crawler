{:namespaces
 ({:source-url nil,
   :wiki-url "news-crawler.core-api.html",
   :name "news-crawler.core",
   :doc nil}
  {:source-url nil,
   :wiki-url "news-crawler.downloader-api.html",
   :name "news-crawler.downloader",
   :doc nil}
  {:source-url nil,
   :wiki-url "news-crawler.parser-api.html",
   :name "news-crawler.parser",
   :doc nil}),
 :vars
 ({:raw-source-url nil,
   :source-url nil,
   :wiki-url "/news-crawler.core-api.html#news-crawler.core/*out-dir*",
   :namespace "news-crawler.core",
   :line 12,
   :file "/home/ogrim/kode/temp/egsiona/src/news_crawler/core.clj",
   :var-type "var",
   :doc "Output directory, where files get stored",
   :name "*out-dir*"}
  {:raw-source-url nil,
   :source-url nil,
   :wiki-url
   "/news-crawler.core-api.html#news-crawler.core/*url-data*",
   :namespace "news-crawler.core",
   :line 16,
   :file "/home/ogrim/kode/temp/egsiona/src/news_crawler/core.clj",
   :var-type "var",
   :doc
   "Defines the sites to scrape, and what filter and parser functions to use\n[[name url article-selector article-filter article-parser][..]]",
   :name "*url-data*"}
  {:arglists ([urls]),
   :name "append-date",
   :namespace "news-crawler.core",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.core-api.html#news-crawler.core/append-date",
   :doc
   "Appends the date to the name in *url-data*\nUsed to create daily folder, and filenames",
   :var-type "function",
   :line 37,
   :file "/home/ogrim/kode/temp/egsiona/src/news_crawler/core.clj"}
  {:arglists ([]),
   :name "current-date",
   :namespace "news-crawler.core",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.core-api.html#news-crawler.core/current-date",
   :doc "Current date as yyyy-m-d",
   :var-type "function",
   :line 23,
   :file "/home/ogrim/kode/temp/egsiona/src/news_crawler/core.clj"}
  {:arglists ([]),
   :name "daily",
   :namespace "news-crawler.core",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url "/news-crawler.core-api.html#news-crawler.core/daily",
   :doc
   "Call with *url-data* and *out-dir* properly set, \nin order to output the resulting data into maps",
   :var-type "function",
   :line 60,
   :file "/home/ogrim/kode/temp/egsiona/src/news_crawler/core.clj"}
  {:arglists ([urls]),
   :name "filter-daily-links",
   :namespace "news-crawler.core",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.core-api.html#news-crawler.core/filter-daily-links",
   :doc
   "Reads in the downloaded front page, and extracts article URLs",
   :var-type "function",
   :line 43,
   :file "/home/ogrim/kode/temp/egsiona/src/news_crawler/core.clj"}
  {:arglists ([filenames parser]),
   :name "parse-articles",
   :namespace "news-crawler.core",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.core-api.html#news-crawler.core/parse-articles",
   :doc
   "Reads in the downloaded articles, and extract the article content",
   :var-type "function",
   :line 53,
   :file "/home/ogrim/kode/temp/egsiona/src/news_crawler/core.clj"}
  {:arglists ([basename urls]),
   :name "urls->vector",
   :namespace "news-crawler.core",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.core-api.html#news-crawler.core/urls->vector",
   :doc
   "Convert a coll of URLs to downloadable format with basename and current date\nExample:\n[\"url1\" \"url2\"] -> [[\"bt_2011-9-6_1\" \"url1\"] [\"bt_2011-9-6_2\" \"url2\"]]",
   :var-type "function",
   :line 29,
   :file "/home/ogrim/kode/temp/egsiona/src/news_crawler/core.clj"}
  {:arglists ([filename url milliseconds]),
   :name "download",
   :namespace "news-crawler.downloader",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.downloader-api.html#news-crawler.downloader/download",
   :doc
   "Download the data in the given URL using HTTP Agents\nArgs:\n  filename - The file name to save the data in\n  url - The URL to fetch\n  milliseconds - Pause before download",
   :var-type "function",
   :line 16,
   :file
   "/home/ogrim/kode/temp/egsiona/src/news_crawler/downloader.clj"}
  {:arglists ([url-data n-streams path]),
   :name "download-all",
   :namespace "news-crawler.downloader",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.downloader-api.html#news-crawler.downloader/download-all",
   :doc
   "Download data in parallel\nArgs:\n  url-data - vector of file names and URLs\n  n-streams - number of parallel downloads\n  path - folder to store files, requires trailing slash\nAdjust (rand-from-to x y) to circumvent anti-crawler scrips",
   :var-type "function",
   :line 30,
   :file
   "/home/ogrim/kode/temp/egsiona/src/news_crawler/downloader.clj"}
  {:arglists ([from to]),
   :name "rand-from-to",
   :namespace "news-crawler.downloader",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.downloader-api.html#news-crawler.downloader/rand-from-to",
   :doc "Random number from - to (both inclusive)",
   :var-type "function",
   :line 11,
   :file
   "/home/ogrim/kode/temp/egsiona/src/news_crawler/downloader.clj"}
  {:arglists ([milliseconds]),
   :name "wait",
   :namespace "news-crawler.downloader",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.downloader-api.html#news-crawler.downloader/wait",
   :doc
   "Halts execution the amount of milliseconds given\nUsefull for avoiding anti-crawler scripts",
   :var-type "function",
   :line 5,
   :file
   "/home/ogrim/kode/temp/egsiona/src/news_crawler/downloader.clj"}
  {:arglists ([html-map selector]),
   :name "all-links",
   :namespace "news-crawler.parser",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.parser-api.html#news-crawler.parser/all-links",
   :doc "Returns all links from the html-map by a given selector",
   :var-type "function",
   :line 12,
   :file "/home/ogrim/kode/temp/egsiona/src/news_crawler/parser.clj"}
  {:arglists ([name must-contain] [name must-contain & or-args]),
   :name "defilter",
   :namespace "news-crawler.parser",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.parser-api.html#news-crawler.parser/defilter",
   :doc
   "Defines filters functions to find articles on the main pages\nLogic:\n  must-contain AND (or-arg1 OR or-arg2 OR ..)",
   :var-type "macro",
   :line 33,
   :file "/home/ogrim/kode/temp/egsiona/src/news_crawler/parser.clj"}
  {:arglists ([name title body]),
   :name "defparser",
   :namespace "news-crawler.parser",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.parser-api.html#news-crawler.parser/defparser",
   :doc
   "Defines parser function to find title and body text from an html-map",
   :var-type "macro",
   :line 51,
   :file "/home/ogrim/kode/temp/egsiona/src/news_crawler/parser.clj"}
  {:arglists ([file]),
   :name "file->map",
   :namespace "news-crawler.parser",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.parser-api.html#news-crawler.parser/file->map",
   :doc "Reads a html file and returns a map",
   :var-type "function",
   :line 6,
   :file "/home/ogrim/kode/temp/egsiona/src/news_crawler/parser.clj"}
  {:arglists ([html-map title body]),
   :name "parse-article",
   :namespace "news-crawler.parser",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.parser-api.html#news-crawler.parser/parse-article",
   :doc
   "Extracts title and body text from a html-map by using selectors",
   :var-type "function",
   :line 42,
   :file "/home/ogrim/kode/temp/egsiona/src/news_crawler/parser.clj"}
  {:arglists ([link]),
   :name "validate-link",
   :namespace "news-crawler.parser",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.parser-api.html#news-crawler.parser/validate-link",
   :doc "Simplistic link validation such as minimum length",
   :var-type "function",
   :line 17,
   :file "/home/ogrim/kode/temp/egsiona/src/news_crawler/parser.clj"})}
