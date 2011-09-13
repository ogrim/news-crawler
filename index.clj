{:namespaces
 ({:source-url nil,
   :wiki-url "news-crawler.core-api.html",
   :name "news-crawler.core",
   :doc nil}
  {:source-url nil,
   :wiki-url "news-crawler.db-api.html",
   :name "news-crawler.db",
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
   :wiki-url "/news-crawler.core-api.html#news-crawler.core/*db*",
   :namespace "news-crawler.core",
   :line 26,
   :file "/home/ogrim/kode/news-crawler/src/news_crawler/core.clj",
   :var-type "var",
   :doc
   "Defines database settings, which are passed to news-crawler.db",
   :name "*db*"}
  {:raw-source-url nil,
   :source-url nil,
   :wiki-url "/news-crawler.core-api.html#news-crawler.core/*out-dir*",
   :namespace "news-crawler.core",
   :line 13,
   :file "/home/ogrim/kode/news-crawler/src/news_crawler/core.clj",
   :var-type "var",
   :doc "Output directory, where files get stored",
   :name "*out-dir*"}
  {:raw-source-url nil,
   :source-url nil,
   :wiki-url
   "/news-crawler.core-api.html#news-crawler.core/*url-data*",
   :namespace "news-crawler.core",
   :line 17,
   :file "/home/ogrim/kode/news-crawler/src/news_crawler/core.clj",
   :var-type "var",
   :doc
   "Defines the sites to scrape, and what filter and parser functions to use.\nThe selector matches articles on the front page, while the filter will reduce\nthe selection, the parser will scrape the article content.\n   [[name url article-selector article-filter article-parser][..]]",
   :name "*url-data*"}
  {:arglists ([]),
   :name "current-date",
   :namespace "news-crawler.core",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.core-api.html#news-crawler.core/current-date",
   :doc "Current date as yyyy-m-d",
   :var-type "function",
   :line 33,
   :file "/home/ogrim/kode/news-crawler/src/news_crawler/core.clj"}
  {:arglists ([html-list parser urls]),
   :name "parse-articles",
   :namespace "news-crawler.core",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.core-api.html#news-crawler.core/parse-articles",
   :doc
   "Takes a list of downloaded articles, urls and the parser.\nExtracts the content of the articles to maps",
   :var-type "function",
   :line 46,
   :file "/home/ogrim/kode/news-crawler/src/news_crawler/core.clj"}
  {:arglists ([]),
   :name "scrape",
   :namespace "news-crawler.core",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url "/news-crawler.core-api.html#news-crawler.core/scrape",
   :doc
   "Runs the scraping with configurations defined by *out-dir*, *url-data* and *db*",
   :var-type "function",
   :line 54,
   :file "/home/ogrim/kode/news-crawler/src/news_crawler/core.clj"}
  {:arglists ([url selector filter-func]),
   :name "url->links",
   :namespace "news-crawler.core",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.core-api.html#news-crawler.core/url->links",
   :doc
   "Extracts the article links from url using selector and filter-function",
   :var-type "function",
   :line 39,
   :file "/home/ogrim/kode/news-crawler/src/news_crawler/core.clj"}
  {:arglists ([db articles]),
   :name "insert-articles",
   :namespace "news-crawler.db",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.db-api.html#news-crawler.db/insert-articles",
   :doc "Inserts all articles into database",
   :var-type "function",
   :line 17,
   :file "/home/ogrim/kode/news-crawler/src/news_crawler/db.clj"}
  {:arglists ([url milliseconds]),
   :name "download",
   :namespace "news-crawler.downloader",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.downloader-api.html#news-crawler.downloader/download",
   :doc
   "Returns the web page as a string\nArgs:\n  url - The URL to fetch\n  milliseconds - Pause before download",
   :var-type "function",
   :line 16,
   :file
   "/home/ogrim/kode/news-crawler/src/news_crawler/downloader.clj"}
  {:arglists ([urls streams]),
   :name "download-all",
   :namespace "news-crawler.downloader",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.downloader-api.html#news-crawler.downloader/download-all",
   :doc
   "Download data in parallel and returns a list of strings\nArgs:\n  url-data - vector of URLs\n  n-streams - number of parallel downloads\nAdjust (rand-from-to x y) to circumvent anti-crawler scrips",
   :var-type "function",
   :line 28,
   :file
   "/home/ogrim/kode/news-crawler/src/news_crawler/downloader.clj"}
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
   "/home/ogrim/kode/news-crawler/src/news_crawler/downloader.clj"}
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
   "/home/ogrim/kode/news-crawler/src/news_crawler/downloader.clj"}
  {:arglists ([html-map selector]),
   :name "all-links",
   :namespace "news-crawler.parser",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.parser-api.html#news-crawler.parser/all-links",
   :doc "Returns all links from the html-map by a given selector",
   :var-type "function",
   :line 17,
   :file "/home/ogrim/kode/news-crawler/src/news_crawler/parser.clj"}
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
   :line 38,
   :file "/home/ogrim/kode/news-crawler/src/news_crawler/parser.clj"}
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
   :line 56,
   :file "/home/ogrim/kode/news-crawler/src/news_crawler/parser.clj"}
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
   :file "/home/ogrim/kode/news-crawler/src/news_crawler/parser.clj"}
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
   :line 47,
   :file "/home/ogrim/kode/news-crawler/src/news_crawler/parser.clj"}
  {:arglists ([string]),
   :name "str->map",
   :namespace "news-crawler.parser",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.parser-api.html#news-crawler.parser/str->map",
   :doc "Reads a html as string and returns a map",
   :var-type "function",
   :line 12,
   :file "/home/ogrim/kode/news-crawler/src/news_crawler/parser.clj"}
  {:arglists ([link]),
   :name "validate-link",
   :namespace "news-crawler.parser",
   :source-url nil,
   :raw-source-url nil,
   :wiki-url
   "/news-crawler.parser-api.html#news-crawler.parser/validate-link",
   :doc "Simplistic link validation such as minimum length",
   :var-type "function",
   :line 22,
   :file "/home/ogrim/kode/news-crawler/src/news_crawler/parser.clj"})}
