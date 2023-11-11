package aula4bis_ex1_v1;

import java.util.LinkedList;
import java.util.List;

public class TextRepository {
	List<TextChunk> chunksToAnalyze;
	// LinkedList melhor quando queremos adicionar e remover, desvantagem - usar mais memória

	public TextRepository(String text, String stringToBeFound, int chunkSize) {
		chunksToAnalyze = new LinkedList<TextChunk>();

		for (int initPos = 0; initPos < text.length(); initPos += chunkSize) {
			int finalPos = Math.min(initPos + chunkSize, text.length());
			TextChunk chunk = new TextChunk(text.substring(initPos, finalPos), initPos, stringToBeFound);
			chunksToAnalyze.add(chunk);
		}

	}

	public synchronized TextChunk getChunk() {
		// TODO Remover um chunk da lista
		// TODO Não é bloquante => Se não houver, devolve null

		if (chunksToAnalyze.isEmpty()) {
			return null;
		}

		return chunksToAnalyze.remove(0);
	}

	public synchronized void submitResult(TextChunk chunk) {
		// TODO VERSAO 2
	}

	public synchronized List<Integer> getAllMatches() throws InterruptedException {
		// TODO VERSAO 2
		
		return null;

	}
}
