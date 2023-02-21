package com.phoemphanith.blogserviceapibasic.entity

import com.fasterxml.jackson.annotation.JsonIgnore
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
    var content: String? = null,
    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonIgnore
    var comment: Set<Comment> = HashSet()
)