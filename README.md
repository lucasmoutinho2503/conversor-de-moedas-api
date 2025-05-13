# 💱 Conversor de Moedas em Java

Este é um projeto simples de **conversor de moedas** desenvolvido em Java, utilizando a **ExchangeRate API** para obter taxas de câmbio em tempo real. O projeto permite ao usuário escolher entre 6 moedas disponíveis, inserir o valor desejado e realizar a conversão.

---

## 📌 Funcionalidades

- Consulta de câmbio usando API externa.
- Conversão de moedas entre 6 opções pré-definidas:
  - USD (Dólar Americano)
  - EUR (Euro)
  - BRL (Real Brasileiro)
  - GBP (Libra Esterlina)
  - JPY (Iene Japonês)
  - AUD (Dólar Australiano)
- Entrada interativa via terminal com `Scanner`.
- Exibição do valor convertido com duas casas decimais.

---

## 🛠 Tecnologias utilizadas

- Java 11+
- `java.net.http.HttpClient` para requisições HTTP
- `com.google.gson.Gson` para tratamento de JSON
- ExchangeRate API (https://www.exchangerate-api.com/)

---

## 🚀 Como executar

### 1. Clonar o repositório

```bash
git clone https://github.com/seu-usuario/conversor-moeda-java.git
cd conversor-moeda-java
