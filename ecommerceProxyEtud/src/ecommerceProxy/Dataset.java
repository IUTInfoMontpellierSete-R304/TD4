package ecommerceProxy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dataset {

	private Collection<Transaction> trans;
	private Article[] articles;
	Instance inst = Instance.instance1;

	private static Dataset dataset;

	private Dataset() throws IOException {

		articles = new Article[inst.nbArticles];

		for (int i = 0; i < inst.nbArticles; i++)
			articles[i] = new Article(i);

		trans = new Collection<Transaction>();

		BufferedReader reader = new BufferedReader(new FileReader(inst.datasetPath));
		String line;
		// for each line (transactions) until the end of the file
		while (((line = reader.readLine()) != null)) {
			// if the line is a comment, is empty or is a
			// kind of metadata
			if (line.isEmpty() == true || line.charAt(0) == '#' || line.charAt(0) == '%' || line.charAt(0) == '@') {
				continue;
			}
			// split the line according to spaces
			String[] lineSplited = line.split(" ");

			// create an array of int to store the items in this transaction
			Transaction transaction = new Transaction();

			// for each item in this line (transaction)
			for (int i = 0; i < lineSplited.length; i++) {

				int item = Integer.parseInt(lineSplited[i]);

				transaction.add(articles[item]);

			}
			trans.add(transaction);

		}

		// close the input file
		reader.close();

	}

	public ArrayList<Motif> motifParser() throws IOException {
		
		//TODO
	}

	/////////////////////////////////// INSTANCES
	/////////////////////////////////// //////////////////////////////////////////////////
	enum Instance {
		toy("ecommerceProxy/data/toy.txt", "ecommerceProxy/data/toymotifs.txt", 0.5f, 5),
		instance1("ecommerceProxy/data/ecommerce.txt", "ecommerceProxy/data/motifs1.txt", 0.5f, 130),
		instance2("ecommerceProxy/data/ecommerce.txt", "ecommerceProxy/data/motifs2.txt", 0.5f, 130),;

		final String datasetPath;
		final String motifsPath;
		final float seuil;
		final int nbArticles;

		Instance(String datasetPath, String motifsPath, float seuil, int nbArticles) {
			this.datasetPath = datasetPath;
			this.motifsPath = motifsPath;
			this.seuil = seuil;
			this.nbArticles = nbArticles;
		}

	}



}
