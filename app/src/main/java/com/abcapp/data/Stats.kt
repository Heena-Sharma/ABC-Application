package com.abcapp.data

data class Stats(val pageCount: Int, val top3Chars: List<Char>, val top3CharsWithCounts: List<Map.Entry<Char, Int>>)