package aula4bis_ex1_v2;

import java.util.LinkedList;
import java.util.List;

public class TextRepository {
	private List<TextChunk> chunksToAnalyze;
	private List<TextChunk> chunksAnalyzed;
	private int numTotalChunks;
	// LinkedList melhor quando queremos adicionar e remover, desvantagem - usar mais memória

	public TextRepository(String text, String stringToBeFound, int chunkSize) {
		chunksToAnalyze = new LinkedList<TextChunk>();

		for (int initPos = 0; initPos < text.length(); initPos += chunkSize) {
			int finalPos = Math.min(initPos + chunkSize, text.length());
			TextChunk chunk = new TextChunk(text.substring(initPos, finalPos), initPos, stringToBeFound);
			chunksToAnalyze.add(chunk);
		}

		numTotalChunks = chunksToAnalyze.size();
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
		// TODO Recebe um chunk analisado
		chunksAnalyzed.add(chunk);

		// Notificar que todos os chunks ja foram analisados
		if (chunksAnalyzed.size() == numTotalChunks) {
			notifyAll();
		}
	}

	public synchronized List<Integer> getAllMatches() throws InterruptedException {

		while (chunksAnalyzed.size() < numTotalChunks) {
			wait();
		}

		return summaryResults();
	}

	private List<Integer> summaryResults() {
		List<Integer> results = new LinkedList<>();
		for (TextChunk chunk: chunksAnalyzed) {
			// Verifica as posicoes => se houver posicoes, adiciona na lista results
			results.addAll(chunk.getFoundPos());
		}
		return results;
	}
}
