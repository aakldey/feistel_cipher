# Feistel cipher
Scala feistel cipher

Encrypt and decrypt string with init vector (Long)

  Encrypt  
  ```scala
    val enc = feistel(text, round, false, key) // text: String, round: Int, key: Long, return String
  ```
  Decrypt
  ```scala
    val dec = feistel(text, round, true, key)
  ```
