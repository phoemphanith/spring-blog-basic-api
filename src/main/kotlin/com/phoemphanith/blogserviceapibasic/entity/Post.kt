package com.phoemphanith.blogserviceapibasic.entity

import jakarta.persistence.*

@Entity
@Table(
    name = "posts",
    uniqueConstraints = [ UniqueConstraint( columnNames = ["title"] ) ]
)
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
    var title: String? = null,
    var description: String? = null,
    var content: String? = null
)