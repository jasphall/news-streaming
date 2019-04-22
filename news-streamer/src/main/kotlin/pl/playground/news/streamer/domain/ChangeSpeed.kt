package pl.playground.news.streamer.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class ChangeSpeed @JsonCreator constructor(
        @JsonProperty("speedInMillis") val speedInMillis: Int
)
