package br.univel.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface IServer extends Remote {

	public static final String NOME_SERVICO = "JShare";

	/**
	 * Recebe informações de um novo cliente.
	 * 
	 * @param c
	 * @throws RemoteException
	 */
	public void registrarCliente(Cliente c) throws RemoteException;

	/**
	 * Recebe a lista de arquivos disponíveis no cliente.
	 * 
	 * @param c
	 * @param lista
	 * @throws RemoteException
	 */
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista)
			throws RemoteException;

	/**
	 * Usado quando um cliente deseja procurar um arquivo pelo nome, o
	 * servidor lê todos os arquivos publicados e retorna uma mapa contendo
	 * os resultados em cada cliente.
<<<<<<< HEAD
	 */
	public List<String> procurarArquivo(String query, TipoFiltro tipoFiltro, String filtro)
			throws RemoteException;

	/**
	 * Recebe informações do arquivo e retorna o arquivo em formato
	 * de array de bytes. 
	 */
	public byte[] baixarArquivo(Cliente cli, Arquivo arq) throws RemoteException;

	/**
	 * Desconecta o cliente, tornando também indisponível seus arquivos
	 * para as buscas. 
	 */
	public void desconectar(Cliente c) throws RemoteException;

	/**
	 * @return
	 */
=======
	 * 
	 * @param nome
	 * @return
	 * @throws RemoteException
	 */
	public Map<Cliente, List<Arquivo>> procurarArquivo(String query, TipoFiltro tipoFiltro, String filtro)
			throws RemoteException;

	/**
	 * Recebe informações do arquivo e retorna o arquivo em formato
	 * de array de bytes. 
	 * 
	 * @param arq
	 * @return
	 * @throws RemoteException
	 */
	public byte[] baixarArquivo(Cliente cli, Arquivo arq) throws RemoteException;

	/**
	 * Desconecta o cliente, tornando também indisponível seus arquivos
	 * para as buscas. 
	 * 
	 * @param c
	 * @throws RemoteException
	 */
	public void desconectar(Cliente c) throws RemoteException;
>>>>>>> branch 'master' of https://github.com/rodh07/Transf-Arquivos-JShared.git

}
