package br.com.alura.screenmatch.modelos;

public class MeuErrodeConversao extends RuntimeException {
	
	private String  msg;
	
	public MeuErrodeConversao(String msg) {
		
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {
		// Sobreescrevendo o método getMessage() da classe RuntimeException para retornar a mensagem personalizada
		return this.msg;
	}

}
