package br.univel.download;

public enum Status {

	TRANSFERIDO("Arquivo transferido com sucesso."),
	TRANSFERINDO("Transferindo arquivo..."),
	ERRO_AO_TRANSFERIR("Erro ao baixar arquivo.");
	
	private String status;

	private Status(String status){
		this.status = status;
	}

	@Override
	public String toString() {
		return status;
	}
}

