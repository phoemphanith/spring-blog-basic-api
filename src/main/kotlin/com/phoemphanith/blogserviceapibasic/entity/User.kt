package com.phoemphanith.blogserviceapibasic.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
    var email: String? = null,
    @Column(nullable = false)
    var name: String? = null,
    @Column(nullable = false)
    var username: String? = null,
    @Column(nullable = false)
    var password: String? = null,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    @JsonIgnore
    var roles: Set<Role>? = HashSet()
)