# Gilded Rose Kata - Java

Este projeto é uma implementação do famoso [Gilded Rose Kata](https://github.com/emilybache/GildedRose-Refactoring-Kata) em Java, um exercício de refatoração usado para praticar técnicas de programação e teste.

## Descrição

O Gilded Rose é uma pequena hospedaria com uma localização privilegiada na cidade. Também compra e vende apenas os melhores produtos. Infelizmente, a qualidade dos produtos degrada constantemente conforme se aproximam da data de venda.

### Regras de Negócio

1. Todos os itens têm um valor `sellIn` que denota quantos dias restam para vender o item
2. Todos os itens têm um valor `quality` que denota o quão valioso é o item
3. No final de cada dia, o sistema reduz ambos os valores para cada item
4. Uma vez que a data de venda passou, a qualidade degrada duas vezes mais rápido
5. A qualidade de um item nunca é negativa
6. "Aged Brie" na verdade aumenta em qualidade conforme envelhece
7. A qualidade de um item nunca é superior a 50
8. "Sulfuras" é um item lendário e nunca precisa ser vendido nem diminui em qualidade
9. "Backstage passes" aumentam em qualidade conforme a data de venda se aproxima:
    - Aumenta 2 quando há 10 dias ou menos
    - Aumenta 3 quando há 5 dias ou menos
    - Qualidade cai para 0 após o concerto

## Executar testes
Para executar os testes, você pode usar o Maven. No terminal, navegue até o diretório do projeto e execute:

```bash
  mvn test
```
