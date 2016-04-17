# Feistel cipher
Scala feistel cipher

Encrypt and decrypt string with init vector (Long)

Usage:
  encrypt  
    val enc = feistel(text, round, false, key) // text: String, round: Int, key: Long, return String
  decrypt 
    val dec = feistel(text, round, true, key)
