package com.app.playotest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

object DataModel {
    class SearchResultModel {
        @SerializedName("hits")
        @Expose
         val hits: List<HitModel>? = null
        @SerializedName("nbHits")
        @Expose
         val nbHits: Int? = 0
        @SerializedName("page")
        @Expose
         val page: Int? = 0
        @SerializedName("nbPages")
        @Expose
         val nbPages: Int? = 0
        @SerializedName("hitsPerPage")
        @Expose
         val hitsPerPage: Int? = 0
        @SerializedName("exhaustiveNbHits")
        @Expose
         val exhaustiveNbHits: Boolean? = false
        @SerializedName("query")
        @Expose
         val query: String? = ""
        @SerializedName("params")
        @Expose
         val params: String? = ""
        @SerializedName("processingTimeMS")
        @Expose
         val processingTimeMS: Int? = 0
    }

    class AuthorModel {
        @SerializedName("value")
        @Expose
         val value: String? = ""
        @SerializedName("matchLevel")
        @Expose
         val matchLevel: String? = ""
        @SerializedName("matchedWords")
        @Expose
         val matchedWords: List<Any>? = null
    }

    class HighlightResultModel {
        @SerializedName("title")
        @Expose
         val title: CommonDetailsModel? = null
        @SerializedName("url")
        @Expose
         val url: CommonDetailsModel? = null
        @SerializedName("author")
        @Expose
         val author: AuthorModel? = null
        @SerializedName("story_text")
        @Expose
         val storyText: CommonDetailsModel? = null
    }


    class HitModel {
        @SerializedName("created_at")
        @Expose
        val createdAt: String? = ""
        @SerializedName("title")
        @Expose
         val title: String? = ""
        @SerializedName("url")
        @Expose
         val url: String? = ""
        @SerializedName("author")
        @Expose
         val author: String? = null
        @SerializedName("points")
        @Expose
         val points: Int? = null
        @SerializedName("story_text")
        @Expose
         val storyText: Any? = null
        @SerializedName("comment_text")
        @Expose
         val commentText: Any? = null
        @SerializedName("num_comments")
        @Expose
         val numComments: Int? = null
        @SerializedName("story_id")
        @Expose
         val storyId: Any? = null
        @SerializedName("story_title")
        @Expose
         val storyTitle: Any? = null
        @SerializedName("story_url")
        @Expose
         val storyUrl: Any? = null
        @SerializedName("parent_id")
        @Expose
         val parentId: Any? = null
        @SerializedName("created_at_i")
        @Expose
         val createdAtI: Int? = null
        @SerializedName("relevancy_score")
        @Expose
         val relevancyScore: Int? = null
        @SerializedName("_tags")
        @Expose
         val tags: List<String>? = null
        @SerializedName("objectID")
        @Expose
         val objectID: String? = null
        @SerializedName("_highlightResult")
        @Expose
         val highlightResult: CommonDetailsModel? = null
    }

    class CommonDetailsModel{
        @SerializedName("value")
        @Expose
         val value: String? = null
        @SerializedName("matchLevel")
        @Expose
         val matchLevel: String? = null
        @SerializedName("matchedWords")
        @Expose
         val matchedWords: List<Any>? = null
    }


}