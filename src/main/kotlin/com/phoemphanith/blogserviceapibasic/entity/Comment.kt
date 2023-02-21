package com.phoemphanith.blogserviceapibasic.entity

import jakarta.persistence.*

@Entity
@Table(name = "comments")
class Comment (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null,
    var email: String? = null,
    var body: String? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    var post: Post? = null
)