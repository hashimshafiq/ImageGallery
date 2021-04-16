package io.ebay.imagegallery.ui.home

fun String.getHighResImageURL() : String = "https://${this}_27.jpg"

fun String.getThumbnailURLLink() : String = "https://${this}_2.jpg"
