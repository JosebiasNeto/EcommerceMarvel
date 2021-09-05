# [Mobile] - Ecommerce Marvel

Trata-se de um aplicativo de ecommerce simples. Possui quatro activities, sendo elas:

MainActivity - página inicial que lista as revistas, mostrando a imagem, o título e o preço. As revistas com estrela na parte superior direito são as marcadas como raras.

DetailsActivity - ao clicar na revista você é direcionado a uma página de detalhes que informa ainda mais: o ano, o tipo de revista e a descrição da mesma. Nessa activity tem um NumberPicker o qual o usuário escolhe a quantidade de revistas, ao alterar a quantidade o valor a ser pago também é alterado.

CheckoutActivity - desta vez aparece a revista e a quantidade escolhida com um campo para inserção de cupom de desconto, o qual altera o valor de compra. Caso o cupom seja raro ele serve em todas as revistas e oferece desconto de 25%, caso seja comum só funciona nas revistas comuns e tem desconto de 10%.

ConfirmationActivity - esta última informa o valor pago e a revista comprada, tendo um botão para voltar a MainActivity.



## Implementação

1. A primeira etapa foi entender o projeto, organizar a arquitetura e projetar os layouts. Para isso criei minha conta na Marvel e com o Postman vi como a API iria me retorna a lista de comics (revistas).
2. Entendendo como a API retornaria, escolhi os objetos que seriam utilizados e montei os layouts. Na MainActivity foi utilizado recyclerview com duas colunas.
3. Próximo passo foi consumir a API com o Retrofit, utilizando as keys disponibilizadas pela Marvel. Após implementar na camada data, passamos os parâmetros para UI utilizando ViewModel.
4. Tendo os parâmetros na MainActivity os valores foram passados por intent para as demais activities de acordo com o click do usuário.
5. Na camada data foi necessário implementar uma lógica no recebimento da lista de revistas da API. Decidi utilizar o tamanho da lista recebida (pois com o tempo pode ser que decidam mandar mais ou menos que 10 revistas por request). Minha lógica pega o tamanho da lista e tira 12% dele para criar uma lista de número aleatórios que variam de 0 a o tamanho da lista menos 1, sendo assim, as revistas que estiverem nessa posição serão setadas como raras aleatoriamente.
6. Nas activities as revistas raras recebem uma estrela amarela na parte superior direita.
7. Para otimizar a velocidade da aplicação e melhorar a experiência do usuário decidi implementar um banco de dados utilizando a biblioteca Room. O aplicativo ficou bem mais rápido. Quando o banco de dados está vazio o app consome a API do servidor e alimenta o banco de dados. Sempre o app consome do banco e depois disso recebe novas informações da API.
8. Também para melhorar a experiência do usuário deixei a UI bem responsiva com as alterações de quantidade e cupons.Fiz também a implementação de um searchview na MainActivity para buscar por títulos e implementei a tradução do aplicativo para o português de acordo com a linguagem escolhida no telefone.
