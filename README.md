# pt-br-normalizer
Normalizer for brazilian portuguese. 

## Application' scope is to apply the following businesses rules:
1. Remove carets
   1. Example: `"áéíóú ÁÉÍÓÚ ãÃ $%.,'-* de da do  das dos üÜ èÈ"` would become `"aeiou AEIOU aA $%.,'-* de da do  das dos uU eE"` 
1. Remove special caracters
   1. Example: `"aeiou AEIOU aA $%.,'-* de da do  das dos uU eE"` would become `"aeiou AEIOU aA  de da do  das dos uU eE"`
1. Capitalize all letters
   1. Example: `"aeiou AEIOU aA  de da do  das dos uU eE"` would become `"AEIOU AEIOU AA  DE DA DO  DAS DOS UU EE"`
1. Remove simple prepositions
   1. Example: `"AEIOU AEIOU AA  DE DA DO  DAS DOS UU EE"` would become `"AEIOU AEIOU AA        UU EE"`
1. Remove possible remaining repeated spaces
   1. Example: `"AEIOU AEIOU AA        UU EE"` would become `"AEIOU AEIOU AA UU EE"`

## Stack
:paperclip: maven \
:loudspeaker: Kotlin

## Materials
1. Test your regex at [regex 101](https://regex101.com)
