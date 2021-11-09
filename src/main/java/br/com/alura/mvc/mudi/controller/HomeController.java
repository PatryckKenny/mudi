package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private PedidoRepository pedidosRepository;

    @GetMapping
    public String home(Model model, Principal principal){

        Sort dataDaEntrega = Sort.by("dataDaEntrega").descending();
        PageRequest page = PageRequest.of(0, 10, dataDaEntrega);

        List<Pedido> pedidos = pedidosRepository.findByStatusPedido(StatusPedido.ENTREGUE, page);
        model.addAttribute("pedidos", pedidos);
        return "home";
    }
}
