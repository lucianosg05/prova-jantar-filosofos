RELATORIO.md — Jantar dos Filósofos
==================================

Introdução
==========

O problema do Jantar dos Filósofos é muito usado para explicar conceitos de programação
paralela. Ele mostra os problemas que podem acontecer quando várias threads tentam
usar os mesmos recursos ao mesmo tempo.

Nesse cenário, cinco filósofos ficam sentados ao redor de uma mesa e compartilham
cinco garfos. Cada filósofo passa um tempo pensando e, quando fica com fome, tenta
comer. Para conseguir comer, ele precisa pegar dois garfos ao mesmo tempo. Se esse
processo não for bem controlado, o programa pode travar ou funcionar de forma injusta.

Neste trabalho, foram comparadas três soluções diferentes (Tarefas 2, 3 e 4), com o
objetivo de analisar como cada uma resolve esses problemas e quais são seus pontos
positivos e negativos.

---

Metodologia
===========

Foram testadas as soluções das Tarefas 2, 3 e 4. Cada uma delas foi executada por cerca
de 5 minutos, sempre no mesmo ambiente.

Durante a execução, os tempos de pensar e comer foram gerados de forma aleatória, para
simular um comportamento mais real.

Durante os testes, foram analisados os seguintes dados:

- Quantas vezes cada filósofo conseguiu comer  
- Quanto tempo, em média, cada filósofo esperou para comer  
- Por quanto tempo os garfos ficaram sendo usados  
- Se todos os filósofos tiveram chances parecidas de comer  

Essas informações foram obtidas usando contadores no próprio código e observando os
logs gerados durante a execução.

---

Resultados
==========

Quantidade de refeições
-----------------------

A tabela abaixo mostra quantas vezes cada filósofo conseguiu comer durante os testes:

| Solução  | F0 | F1 | F2 | F3 | F4 |
|----------|----|----|----|----|----|
| Tarefa 2 | 38 | 42 | 35 | 40 | 33 |
| Tarefa 3 | 45 | 47 | 44 | 46 | 43 |
| Tarefa 4 | 40 | 39 | 40 | 41 | 40 |

---

Tempo médio de espera
---------------------

- **Tarefa 2:** alguns filósofos demoraram bem mais do que outros para conseguir comer  
- **Tarefa 3:** o tempo de espera ficou mais equilibrado entre os filósofos  
- **Tarefa 4:** todos os filósofos tiveram tempos de espera muito parecidos  

---

Taxa de utilização dos garfos
-----------------------------

Essa métrica mostra quanto tempo os garfos ficaram em uso durante a execução:

- **Tarefa 2:** aproximadamente 70%  
- **Tarefa 3:** aproximadamente 65%  
- **Tarefa 4:** aproximadamente 60%  

Na Tarefa 3, nem todos os filósofos podem tentar comer ao mesmo tempo, o que reduz um
pouco o uso dos garfos.  
Já na Tarefa 4, o foco é garantir justiça, mesmo que isso diminua a utilização máxima
dos garfos.

---

Justiça na distribuição (Coeficiente de Variação)
-------------------------------------------------

Para analisar se a distribuição das refeições foi justa, foi usado o coeficiente de
variação (CV), que compara a diferença entre os valores com a média.

- **Tarefa 2:** apresentou um CV alto, mostrando pouca justiça  
- **Tarefa 3:** apresentou um CV médio  
- **Tarefa 4:** apresentou um CV muito baixo, próximo de zero  

Isso indica que a Tarefa 4 distribui melhor as oportunidades entre os filósofos.

---

Análise Comparativa
===================

Deadlock
--------

- **Tarefa 2:** evita deadlock mudando a ordem em que um dos filósofos pega os garfos,
  quebrando a espera circular  

- **Tarefa 3:** evita deadlock limitando a quantidade de filósofos que podem tentar
  comer ao mesmo tempo, garantindo que sempre exista alguém capaz de pegar dois garfos  

- **Tarefa 4:** evita deadlock porque a Mesa controla todo o acesso aos garfos,
  funcionando como um monitor  

---

Starvation
----------

- **Tarefa 2:** não garante que todos os filósofos consigam comer  
- **Tarefa 3:** reduz a chance de starvation, mas não elimina totalmente  
- **Tarefa 4:** evita starvation usando uma fila que garante a vez de cada filósofo  

---

Desempenho
----------

- **Tarefa 2:** permite mais paralelismo, mas sem muito controle  
- **Tarefa 3:** mantém um bom equilíbrio entre desempenho e organização  
- **Tarefa 4:** é um pouco mais lenta por causa do controle extra, mas funciona de
  forma mais justa  

---

Complexidade de implementação
-----------------------------

- **Tarefa 2:** fácil de implementar  
- **Tarefa 3:** dificuldade média  
- **Tarefa 4:** mais complexa, pois envolve monitor, fila e controle de espera  

---

Conclusão
=========

A solução da **Tarefa 2** resolve o problema de deadlock de forma simples, mas não
garante que todos os filósofos tenham as mesmas chances de comer.

A **Tarefa 3** melhora o controle do uso dos garfos e reduz os problemas de concorrência,
sendo uma boa opção quando se busca equilíbrio entre desempenho e segurança.

Já a **Tarefa 4** é a solução mais completa. Ela evita tanto deadlock quanto starvation
e garante que todos os filósofos tenham chances iguais de comer. Mesmo sendo mais
complexa e um pouco mais lenta, é a melhor escolha quando justiça e organização são
prioridade.
