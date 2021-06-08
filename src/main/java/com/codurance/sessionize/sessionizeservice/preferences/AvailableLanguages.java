package com.codurance.sessionize.sessionizeservice.preferences;

import java.util.Arrays;
import java.util.List;

public class AvailableLanguages {

    public static List<Language> get() {
        return Arrays.asList(
                new Language("JAVA", "Java"),
                new Language("CSHARP", "C#"),
                new Language("GOLANG", "Go"),
                new Language("CPP", "C++"),
                new Language("C", "C"),
                new Language("RUST", "Rust"),
                new Language("KOTLIN", "Kotlin"),
                new Language("FSHARP", "F#"),
                new Language("CLOJURE", "Clojure"),
                new Language("PYTHON", "Python"),
                new Language("JS", "JavaScript"),
                new Language("TS", "TypeScript"),
                new Language("SCALA", "Scala"),
                new Language("HASKELL", "Haskell"),
                new Language("RACKET", "Racket"),
                new Language("RACKET", "Racket"),
                new Language("CP", "Common Lisp"),
                new Language("VB", "Visual Basic"),
                new Language("RUBY", "Ruby"),
                new Language("GROOVY", "Groovy"),
                new Language("OCAML", "OCaml"),
                new Language("PHP", "PHP"),
                new Language("SCHEME", "Scheme"),
                new Language("PERL", "Perl"),
                new Language("SWIFT", "Swift"),
                new Language("R", "R"),
                new Language("FORTRAN", "Fortran"),
                new Language("COBOL", "Cobol"),
                new Language("PASCAL", "Pascal"),
                new Language("DART", "Dart"),
                new Language("JULIA", "Julia"),
                new Language("ELIXIR", "Elixir"),
                new Language("ELM", "Elm")

        );
    }

}
