/**
 * 
 */
package br.univel.download;
import java.io.Serializable;
import javax.net.ssl.SSLEngineResult.Status;

/**
 * @author Seven
 *
 */
public class Downloader implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	 * @return 
	 */
	public Downloader setDown(String down) {
		this.down = down;
		return this;
	}
	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 * @return 
	 */
	public Downloader setStatus(Status status) {
		this.status = status;
		return this;
	}
	/**
	 * @return the nomeArquivo
	 */
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	/**
	 * @param nomeArquivo the nomeArquivo to set
	 * @return 
	 */
	public Downloader setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
		return this;
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
