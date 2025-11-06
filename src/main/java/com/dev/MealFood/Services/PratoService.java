package com.dev.MealFood.Services;

import com.dev.MealFood.Enums.PratoCategoria;
import com.dev.MealFood.Exceptions.PratoNaoEncontradoException;
import com.dev.MealFood.Models.Prato;
import com.dev.MealFood.Repositories.PratoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PratoService {

    private PratoRepository pratoRepository;

    public PratoService(PratoRepository pratoRepository) {
        this.pratoRepository = pratoRepository;
    }

    public Prato createPrato(Prato prato) {
        if (Objects.isNull(prato)) {
            throw new RuntimeException("Prato invalido");
        }
        if (prato.getCategoria() == PratoCategoria.PRINCIPAL) {
            if (prato.getIngredientes().size() < 3) {
                throw new RuntimeException("Prato principal deve ter pelo menos 3 ingredientes");
            }
        }
        return pratoRepository.save(prato);
    }

    public Prato findPratoByName(String nome) {
        Prato prato = pratoRepository.findPratoByName(nome);
        if(prato == null){
            throw new PratoNaoEncontradoException(nome);
        }
        return prato;
    }

    public Prato findPratoById(Long id) {
        return pratoRepository.findById(id).orElseThrow(()-> new RuntimeException("prato nao encontrado"));
    }

    public Prato updatePratoByName(String nome, Prato pratoNovo){
        Prato prato = pratoRepository.findPratoByName(nome);
        if (prato != null){
            prato.setNome(pratoNovo.getNome());
            prato.setPreco(pratoNovo.getPreco());
            prato.setCategoria(pratoNovo.getCategoria());
            prato.setIngredientes(pratoNovo.getIngredientes());
            return pratoRepository.save(prato);
        }
        throw new RuntimeException("Prato nao encontrado");
    }

    public List<Prato> findAll() {
        return pratoRepository.findAll();
    }
    public void deletarPrato(Long id){
        pratoRepository.deleteById(id);
    }
    public List<Prato> findByIngredientName(String nome) {
        return pratoRepository.findPratosWithIngredientName(nome);
    }

    public List<Prato> findWithoutIngredientName(String nome) {
        return pratoRepository.findPratosWithoutIngredientName(nome);
    }


}
