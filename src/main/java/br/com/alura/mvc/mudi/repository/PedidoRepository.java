package br.com.alura.mvc.mudi.repository;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Cacheable("status")
    List<Pedido> findByStatusPedido(StatusPedido statusPedido, Pageable page);

    @Query("SELECT p FROM Pedido p join p.user u WHERE u.username = :username")
    List<Pedido> findAllByUsuario(@Param("username") String username);

    @Query("SELECT p FROM Pedido p join p.user u WHERE u.username = :username AND p.statusPedido = :status")
    List<Pedido> findByStatusAndUsuarioPedido(@Param("status") StatusPedido statusPedido, @Param("username")String username);
}
