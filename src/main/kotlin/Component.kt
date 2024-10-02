enum class Component(val value: Char) {
    Coin('$'),
    CollectedCoin('.'),
    Ghost('*'),
    Tree('!');
}

const val map1 = "---$----*---$---" +
        "----*--*----$---" +
        "-----*--$----*--" +
        "---$------*-----" +
        "-----*----$-----"