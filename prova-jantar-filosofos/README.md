# Jantar dos Filósofos – Programação Paralela

Este projeto apresenta diferentes soluções para o problema do **Jantar dos Filósofos**,
que é um exemplo clássico usado para estudar programação paralela e concorrente.

---

## Estrutura do Projeto

src/
├── tarefa1/
├── tarefa2/
├── tarefa3/
└── tarefa4/
README.md  
RELATORIO.md  

---

Descrição das Tarefas
=====================

Tarefa 1 — Implementação Básica (com Deadlock)
----------------------------------------------

Nesta primeira versão, cada filósofo tenta pegar primeiro o garfo da esquerda e depois
o da direita. Para evitar que dois filósofos usem o mesmo garfo ao mesmo tempo, foi
utilizado o `synchronized`.

Essa solução pode gerar deadlock. Isso acontece porque todos os filósofos podem pegar
o garfo da esquerda ao mesmo tempo e ficar esperando pelo garfo da direita, que está
com outro filósofo.

Durante a execução do programa por mais de 30 segundos, esse problema pôde ser
observado. Em determinado momento, todos os filósofos ficaram travados tentando pegar
o segundo garfo, e nenhuma nova mensagem apareceu no log, caracterizando o deadlock.

---

Tarefa 2 — Prevenção de Deadlock
--------------------------------

Nesta versão, o deadlock é evitado mudando a ordem em que um dos filósofos pega os
garfos. Enquanto a maioria pega primeiro o garfo da esquerda, o filósofo de ID 4 pega
primeiro o garfo da direita.

Essa mudança quebra a situação em que todos ficam esperando uns pelos outros ao mesmo
tempo. Com isso, o programa continua funcionando sem travar.

Durante a execução por pelo menos 2 minutos, não foi observado deadlock. Os filósofos
continuaram alternando normalmente entre pensar e comer.

Mesmo assim, ainda pode ocorrer starvation, pois não existe um controle que garanta
que todos os filósofos comam a mesma quantidade de vezes.

Comparando com a Tarefa 1, essa solução resolve o deadlock, mas ainda não garante
justiça no acesso aos garfos.

---

Tarefa 3 — Uso de Semáforos
--------------------------

Nesta solução, foi usado um semáforo para limitar quantos filósofos podem tentar pegar
os garfos ao mesmo tempo. O semáforo permite que no máximo quatro filósofos tentem
comer simultaneamente.

Antes de pegar os garfos, o filósofo precisa obter uma permissão do semáforo. Após
terminar de comer, essa permissão é liberada para que outro filósofo possa tentar
comer. Os garfos continuam sendo protegidos com sincronização.

Essa abordagem evita deadlock porque nunca permite que os cinco filósofos tentem pegar
os garfos ao mesmo tempo. Sempre existe pelo menos um filósofo fora da região crítica,
o que impede a formação de uma espera circular completa.

Comparando com a Tarefa 2, essa solução é mais segura, pois não depende da ordem em que
os garfos são pegos. Em contrapartida, o número de filósofos comendo ao mesmo tempo
pode ser um pouco menor.

A principal vantagem dessa solução é a simplicidade e a eficiência para evitar
deadlock. A desvantagem é que ela ainda não garante justiça total entre os filósofos.

---

Tarefa 4 — Monitores e Fairness
-------------------------------

Nesta solução, foi criada uma classe chamada `Mesa`, responsável por controlar todo o
acesso aos garfos. Todos os filósofos precisam pedir os garfos para a Mesa, que decide
quando eles podem comer.

Para garantir que todos tenham chance de comer, foi usada uma fila que organiza a
ordem de atendimento dos filósofos. Um filósofo só pode pegar os garfos quando chega
sua vez na fila e quando os dois garfos estão disponíveis.

A comunicação entre as threads é feita usando `wait()` e `notifyAll()`. Quando um
filósofo não pode comer, ele fica esperando. Quando outro termina de comer e solta os
garfos, todos são avisados para verificar novamente se podem continuar.

Essa solução evita deadlock porque a Mesa controla o acesso aos recursos de forma
centralizada. Além disso, o uso da fila garante que nenhum filósofo fique esperando
para sempre, evitando starvation.

Comparando com as soluções anteriores, essa é a solução mais segura. Por outro lado,
ela é mais complexa de implementar e pode ser um pouco mais lenta, já que tudo passa
pela Mesa.

O trade-off dessa abordagem é abrir mão de um pouco de desempenho para ganhar mais
organização, segurança e justiça.

---

Compilação e Execução
====================

O projeto foi desenvolvido em **Java**.

Para compilar uma tarefa:

    javac src/tarefaX/*.java

Para executar:

    java tarefaX.JantarDosFilosofos

(Substitua `X` pelo número da tarefa desejada)

---

Execução dos Testes
==================

Cada tarefa (2, 3 e 4) foi executada por aproximadamente **5 minutos**, conforme
solicitado no enunciado, para a coleta das métricas.

Durante a execução, os logs registram as seguintes ações:

- Pensar  
- Tentar pegar os garfos  
- Comer  
- Soltar os garfos  

---

Relatório Comparativo
====================

O relatório comparativo com a análise detalhada das soluções está disponível no
arquivo abaixo:

**[RELATORIO.md](RELATORIO.md)**

---
