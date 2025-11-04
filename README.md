# 🧵 Relatório Comparativo — Modelos de Threads N:M x 1:1

**Autores:** Marco Capote, Raissa Queiroz, Rafael Sene  

---

## 📘 Introdução

Esse relatório consiste em uma simulação dos modelos **N:M** e **1:1**, para comparação de desempenho.  
Ambos os modelos foram implementados em **Java**, e os resultados foram representados em tabela e gráfico para análise comparativa.

---

## ⚙️ Modelo N:M

No modelo **N:M**, várias *threads de usuário* são multiplexadas sobre um número menor de *threads do sistema*.  
Na simulação, isso é representado por um **pool fixo de threads** que executa várias tarefas (threads de usuário).  
Quando uma tarefa é submetida, ela é atribuída a uma das threads do pool, que compartilha o tempo de execução entre as demais tarefas.

Esse modelo permite um bom equilíbrio entre **desempenho e consumo de recursos**, pois reduz o número de threads reais em execução ao mesmo tempo, evitando sobrecarga excessiva do sistema operacional.

---

## 💻 Modelo 1:1

No modelo **1:1**, cada *thread de usuário* corresponde diretamente a uma *thread real* do sistema operacional.  
Isso significa que cada tarefa executa de forma independente, aproveitando melhor o **paralelismo em processadores multicore**.

Entretanto, esse modelo tende a consumir mais recursos, pois cada thread possui sua própria pilha e contexto de execução.  
Em sistemas com muitas threads, isso pode causar **sobrecarga** e reduzir a eficiência geral.

---

## 📊 Resultados

Os programas foram executados com quantidades variadas de threads:  
**10, 25, 75, 100, 500 e 1000**, para uma análise mais rica.
> *<img src="https://i.imgur.com/Vbo9wQf.png">*

### 📈 Versão em gráfico para uma visualização melhor:
> *<img src="https://i.imgur.com/YgDj3NG.png" alt="texto alternativo">*

---

## 🧩 Conclusão

A análise dos resultados demonstra que o desempenho dos modelos varia de acordo com a quantidade de threads utilizadas.

Com **menos threads**, o **modelo 1:1** apresentou melhor desempenho, pois cada thread de usuário foi diretamente mapeada para uma thread do sistema operacional, permitindo **execução paralela real** e **menor tempo de gerenciamento**.

Entretanto, conforme o número de threads aumentou, o **modelo N:M** se mostrou mais eficiente.  
Isso ocorre porque o pool de threads do modelo N:M limita o número de threads do sistema ativas simultaneamente, reduzindo o custo de criação e troca de contexto (*context switch*).  
Assim, o modelo N:M apresenta **melhor escalabilidade** em cenários com alto volume de tarefas concorrentes, onde o modelo 1:1 tende a sofrer com overhead de gerenciamento pelo sistema operacional.


