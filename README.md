# pt-br-normalizer
Normalizer for brazilian portuguese. 

## Application' scope is to apply the following businesses rules:
1. Remove carets
   1. Example: "áéíóú ÁÉÍÓÚ ãÃ $%.,'-* de da do des das dos üÜ èÈ" would become "aeiou AEIOU aA $%.,'-* de da do des das dos uU eE" 
2. Remove special caracters
   1. Example: "aeiou AEIOU aA $%.,'-* de da do des das dos uU eE" would become "aeiou AEIOU aA  de da do des das dos uU eE"
3. Capitalize all letters
   1. Example: "aeiou AEIOU aA  de da do des das dos uU eE" would become "AEIOU AEIOU AA  DE DA DO DES DAS DOS UU EE"
4. Remove simple prepositions
   1. Example: "AEIOU AEIOU AA  DE DA DO DES DAS DOS UU EE" would become "AEIOU AEIOU AA        UU EE"
5. Remove possible remaining repeated spaces
   1. Example: "AEIOU AEIOU AA        UU EE" would become "AEIOU AEIOU AA UU EE"

## Stack
:paperclip: maven \
:loudspeaker: Kotlin

