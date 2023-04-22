package Agora.agora

import org.springframework.data.jpa.repository.JpaRepository

interface ProdutoRepository:JpaRepository<ProdutoDto, Int> {
}