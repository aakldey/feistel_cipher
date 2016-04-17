# Feistel cipher
Scala feistel cipher

Encrypt and decrypt string with init vector (Long)

Usage:
  encrypt  
  ```scala
    val enc = feistel(text, round, false, key) // text: String, round: Int, key: Long, return String
  ```
  decrypt
  ```scala
    val dec = feistel(text, round, true, key)
  ```
