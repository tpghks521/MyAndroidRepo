package com.tpghks5321.aacrepo.data

//data class KakaoImage(val image_url: String, val siteName: String)


class KakaoImage {
	var collection: String? = null

	var display_sitename: String? = null
	var doc_url: String? = null
	var height = 0
	var image_url: String? = null
	var thumbnail_url: String? = null
	var width = 0
}

class Meta {
	var is_end = false
	var pageable_count = 0
	var total_count = 0
}

class KakaoImageList(var documents: List<KakaoImage>? = null,
					 var meta: Meta? = null)


