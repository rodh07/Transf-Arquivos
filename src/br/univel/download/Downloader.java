/**
 * 
 */
package br.univel.download;

import javax.net.ssl.SSLEngineResult.Status;

/**
 * @author Seven
 *
 */
public class Downloader {

	
	private String down;
	private Status status;
	private String nomeArquivo;
	public byte[] dadosDown;
	/**
	 * @return the down
	 */
	public String getDown() {
		return down;
	}
	/**
	 * @param down the down to set
	 */
	public void setDown(String down) {
		this.down = down;
	}
	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	/**
	 * @return the nomeArquivo
	 */
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	/**
	 * @param nomeArquivo the nomeArquivo to set
	 */
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	/**
	 * @return the dadosDown
	 */
	public byte[] getDadosDown() {
		return dadosDown;
	}
	/**
	 * @param dadosDown the dadosDown to set
	 */
	public Downloader setDadosDown(byte[] dadosDown) {
		this.dadosDown = dadosDown;
		return this;
		
	}
	
	
}
