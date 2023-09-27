package com.telmopanacas.bookedapi.Security.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.telmopanacas.bookedapi.Models.Avaliacao;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String displayName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "upvoted_avaliacoes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "avaliacao_id")
    )
    private List<Avaliacao> upvotedAvaliacoes = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "downvoted_avaliacoes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "avaliacao_id")
    )
    private List<Avaliacao> downvotedAvaliacoes = new ArrayList<>();

    public User(Integer id, String displayName, String email, String password, Role role, List<Avaliacao> avaliacoes) {
        this.id = id;
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.avaliacoes = avaliacoes;
    }

    public User(String displayName, String email, String password, Role role) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public User() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<Avaliacao> getUpvotedAvaliacoes() {
        return upvotedAvaliacoes;
    }
    public void setUpvotedAvaliacoes(List<Avaliacao> upvotedAvaliacoes) {
        this.upvotedAvaliacoes = upvotedAvaliacoes;
    }

    public List<Avaliacao> getDownvotedAvaliacoes() {
        return downvotedAvaliacoes;
    }

    public void setDownvotedAvaliacoes(List<Avaliacao> downvotedAvaliacoes) {
        this.downvotedAvaliacoes = downvotedAvaliacoes;
    }

    public List<Integer> getUpvotedAvaliacoesIds() {
        List<Integer> upvotedIds = new ArrayList<>();
        for (Avaliacao avaliacao : this.upvotedAvaliacoes) {
            upvotedIds.add(avaliacao.getId());
        }
        return upvotedIds;
    }

    public List<Integer> getDownvotedAvaliacoesIds() {
        List<Integer> downvotedIds = new ArrayList<>();
        for (Avaliacao avaliacao : this.downvotedAvaliacoes) {
            downvotedIds.add(avaliacao.getId());
        }
        return downvotedIds;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
