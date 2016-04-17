def feistelFunction(block: Int, round: Int): Int = {
    block + (round ^ 1234567873)
  }

def feistel(text: String, rounds: Int, decrypt: Boolean = false, initVec: Long): String = {
    val leftKey = java.lang.Long.parseLong(binaryFill(initVec).substring(0, 32), 2).toInt
    val rightKey = java.lang.Long.parseLong(binaryFill(initVec).substring(32, 64), 2).toInt
    
    val blocks = text.toArray.grouped(4).toList.map(block => block.length match {
      case 4 => binaryFill(block.map(ch => binaryFill(ch)).mkString(""), 64)
      case x: Int => binaryFill(block.map(ch => binaryFill(ch)).mkString("")+fillNull((4-x)*16), 64)
    })
    
    val binary = blocks.map(block => {
      var left = decrypt match {
        case true => java.lang.Long.parseLong(block.substring(0, 32), 2).toInt
        case false => java.lang.Long.parseLong(block.substring(0, 32), 2).toInt ^ leftKey
      }
      var right = decrypt match {
        case true => java.lang.Long.parseLong(block.substring (32, 64), 2).toInt
        case false => java.lang.Long.parseLong(block.substring (32, 64), 2).toInt ^ rightKey
      }
      val range = decrypt match { case true => (1 until rounds + 1).reverse case false => (1 until rounds + 1)}
      for(r <- range) {
        val tmp = right ^ feistelFunction(left, r)
        right = left
        if(decrypt match { case true => r == 1 case false => r == rounds })
          right = tmp
        else
          left = tmp
      }
      decrypt match {
        case true => binaryFill(left ^ leftKey) + binaryFill(right ^ rightKey)
        case false => binaryFill(left) + binaryFill(right)
      }
    
    })
    
    val str = binary.flatMap(block => {
      (block.toArray.grouped(16).map(a => Integer.parseInt(a.mkString(""), 2).toChar))
    }).mkString("")
    str
}



def binaryFill(str: String, base: Int): String = {
    str.length match {
      case base => str
      case x: Int => fillNull(base - x - 1) + str
    }
}

def binaryFill(long: Char): String = {
    val str = long.toBinaryString
    str.length match {
      case 16 => str
      case x: Int => fillNull(16 - x - 1) + str
    }
}

def binaryFill(long: Long): String = {
    val str = long.toBinaryString
    str.length match {
      case 64 => str
      case x: Int => fillNull(64 - x - 1) + str
    }
}

def binaryFill(int: Int): String = {
    val str = int.toBinaryString
    str.length match {
      case 32 => str
      case x: Int => fillNull(32 - x - 1) + str
    }
}

def fillNull(num: Int): String = {
    var str = ""
    for(i <- 0 to num) {
      str +="0"
    }
    str
}
