# Bem-vindo ao teste Android da CINQ!

Aqui, iremos avaliar suas capacidades técnicas referentes à vaga a qual você se candidatou.

A premissa deste teste é desenvolver um aplicativo onde seja possível realizar um cadastro de usuário, informando E-mail e Senha, e a partir deste cadastro, ser possível entrar na aplicação, realizando um processo de Autenticação.

Para iniciar o teste, faça um fork deste repositório e, ao fim do desafio, envie por e-mail o link da versão final, contendo o aplicativo compilado para a realização dos testes (o APK pode estar em uma pasta chamada /dist).

Os requisitos mínimos/esperados para a entrega do desafio estão listados abaixo:

	1 - Desenvolver uma aplicação utilizando os seguintes padrões:
		Arquitetura MVP
		SQLite
		Storage Preferences
		Conexão HTTP - REST
		
	2 - Desenvolver uma tela onde seja possível realizar o Login, contendo os seguintes campos:
		E-mail
		Senha
		Botão para Entrar
		Link para a Tela de Cadastro
		
	3 - Desenvolver uma tela onde seja possível realizar o cadastro do usuário, persistindo na base local do APP (SQLite), contendo os seguintes campos:
		E-mail
		Nome
		Senha
		Botão para Salvar
		
	4 - Desenvolver uma tela inicial, após o Login, onde seja possível visualizar as seguintes informações:
		Nome do Usuário Logado
		Lista de Usuários cadastrados na Aplicação
		Possibilidade de filtrar a lista acima
		Botão para Adicionar Novos Usuários, redirecionando para a tela do Passo 2
		Botão para Editar Usuários, redirecionando para a tela do Passo 2
		Botão para Excluir um Usuário, dando uma confirmação antes de realizar a ação
		
	5 - Na tela inicial, deve-se apresentar um Menu, com as opções:
		Home
		Lista de Albums
		
	6 - Desenvolver uma tela para a Lista de Albums, contendo os seguintes campos:
		Utilizar um adaptar na lista, exibindo apenas o título
		
Regras de Negócio
	
	1 - Tipos de Dados:
		E-mail  	String(50) Not Null
		Nome  		String(50) Not Null
		Senha 	 	String(50) Not Null
		
	2 - Na tela de Login, dar feedback de campos obrigatórios, e informar usuário sobre credenciais inválidas
	3 - Na tela de Cadastro, dar feedback de campos obrigatórios, e mensagem de sucesso quando houver tal
	4 - Na tela inicial, o campo de filtro deve ser do tipo texto, com tamanho máximo de 50 caracteres; em tempo real de digitação, a lista deve ser atualizada
	5 - Ao Editar um usuário, não deve ser possível mudar o E-mail, somente Nome e Senha
	6 - Ao Excluir um usuário, se o registro à ser excluído for o mesmo do usuário logado, deve haver um feedback impedindo a ação
	7 - Na tela de Lista de Albums, as entidades à serem exibidas deverão vir do seguinte endereço: https://jsonplaceholder.typicode.com/albums, utilizando protocolo HTTP
