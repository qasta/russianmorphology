Russian and English morphology for java and lucene 3.0 framework based on open source dictionary from site [АОТ](http://aot.ru). It use dictionary base morphology with some heuristics for unknown words. It support homonym for example for Russian word "вина" it gives two variants "вино" and "вина".


# How to use #

First download [morph-1.0.jar](http://russianmorphology.googlecode.com/files/morph-1.0.jar)  and add it to your class path. When download [Russian](http://russianmorphology.googlecode.com/files/russian-1.0.jar) or [English](http://russianmorphology.googlecode.com/files/english-1.0.jar) package.

If you use maven you can add dependency
```
<!-- For Russian morphology -->
        <dependency>
            <groupId>org.apache.lucene.morphology</groupId>
            <artifactId>russian</artifactId>
            <version>1.0</version>
        </dependency>

<!-- For English morphology -->
        <dependency>
            <groupId>org.apache.lucene.morphology</groupId>
            <artifactId>english</artifactId>
            <version>1.0</version>
        </dependency>
```

Don't forget add link to repository

```
 <repositories>
    ...............
    <repository>
       <id>russian-morphology.lucene.apache.org</id>
       <name>Lucene Russian Morphology Repository for Maven</name>
       <url>http://russianmorphology.googlecode.com/svn/repo/releases/</url>
    </repository>
 </repositories>
```


Now you can create a Lucene Analyzer

```
        RussianAnalayzer russian = new RussianAnalayzer();
        EnglishAnalayzer english = new EnglishAnalayzer();
```

You can write you own analyzer using filter that convert word in it's right forms.

```
       LuceneMorphology luceneMorph = new EnglishLuceneMorphology();
       TokenStream tokenStream = new MorphlogyFilter(result, luceneMorph);
```

Because usually LuceneMorphology contains a lot data needing for it functionality, it is better didn't create this object for each MorphologyFilter.

Also if you need get a list of base forms of word, you can use following example

```
     LuceneMorphology luceneMorph = new EnglishLuceneMorphology();
     List<String> wordBaseForms = luceneMorph.getMorphInfo(word);
```

# Restrictions #

  1. It works only with UTF-8.
  1. It assume what letters е and ё are the same.
  1. Word forms with prefixes like "наибольший" treated as separate word.