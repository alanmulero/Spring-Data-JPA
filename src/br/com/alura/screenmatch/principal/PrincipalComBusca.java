package br.com.alura.screenmatch.principal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;

public class PrincipalComBusca {

	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner leitura = new Scanner(System.in);
		System.out.println("Digite o nome do filme para busca: ");
		var busca = leitura.nextLine();
		String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=37a0cceb";

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();

		client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(System.out::println)
				.join();

		// Response

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		
		var json = response.body();
		System.out.println(json);
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		
		
		TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
		
		// ************************** Usando minha classe Titulo para exibir os dados **************************
		
		Titulo meuTitulo = new Titulo(meuTituloOmdb);

		System.out.println("************************");
		System.out.println("Nome do filme: " + meuTituloOmdb.title());
		System.out.println("Ano de lançamento: " + meuTituloOmdb.year());
		
		System.out.println("Imprimindo minha classe Titulo: ");
		System.out.println("Nome do filme: " + meuTitulo);
	
		
		

	}

}
