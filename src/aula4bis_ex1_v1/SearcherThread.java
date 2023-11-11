package aula4bis_ex1_v1;

class SearcherThread extends Thread {
	private TextRepository textRepository;

	public SearcherThread(TextRepository textRepository, int id) {
		super("SearchTh-" + id);
		this.textRepository = textRepository;
	}

	@Override
	public void run() {
		// TODO Enquanto houver chunks
		// TODO remover um chunk (getChunk)
		// TODO processa o chunk => imprimir todas as posicoes encontradas

		TextChunk chunk = textRepository.getChunk();
		while(chunk != null) {
			processChunk(chunk);

			// Avança par ao proximo chunk
			chunk = textRepository.getChunk();
		}

	}

	private void processChunk(TextChunk chunk) {
		// TODO Imprime todas as posições encontradas
		int fromIndex = 0;

		while(fromIndex < chunk.text.length()) {
			int foundPos = chunk.text.indexOf(chunk.stringToBeFound, fromIndex);
			if (foundPos < 0) {
				break;
			}
			System.out.println(getName() + " found text at " + (chunk.getInitialPos() + foundPos));

			fromIndex = foundPos + chunk.stringToBeFound.length();
		}


	}


}
