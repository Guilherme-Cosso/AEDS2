class Matriz {
	private Celula inicio;
	private Celula fim;

	public Matriz() {
		inicio = new Celula();
		fim = inicio;
	}

	public void CriarM(int n, int m) {
		Celula tmp = fim = inicio;
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < m - 1; i++) {
				fim.dir = new Celula();
				fim.dir.esq = fim;
				fim = fim.dir;
			}
			if (j != (n - 1)) {
				tmp.inf = new Celula();
				tmp.inf.sup = tmp;
				tmp = fim = tmp.inf;
			}
		}

		Celula linha = tmp = inicio;
		if (n != 1) {
			for (int i = 1; i < m ; i++) {
				fim = linha;
				linha = tmp = linha.dir;
				fim = fim.inf.dir;
				for (int j = 1; j < n; j++) {
					tmp.inf = fim;
					fim.sup = tmp;
					tmp = fim;
					if (j != (n - 1)) {
						fim = fim.esq.inf.dir;
					}
				}
			}
		}
		fim = inicio;
	}

	public void inserir(int n) {
		Celula tmp = fim = inicio;
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < n; i++) {
				tmp.elemento = MyIO.readInt();
				tmp = tmp.dir;
			}
			fim = tmp = fim.inf;
		}
		fim = inicio;
	}

	public void mostrar() {
		Celula tmp = inicio;

		for (Celula j = inicio; j != null; j = j.inf) {
			for (Celula i = tmp; i != null; i = i.dir) {
				System.out.print("| " + i.elemento + " ");
			}
			System.out.print("|\n");
			tmp = j.inf;
		}
	}

	public void mov() {
		Celula tmp = inicio;
		Boolean esq, dir, up, down, zero , parar;
		esq = dir = parar = up = zero = down = false;
		int num = 0;
		for (Celula j = inicio; j != null; j = j.inf) {
			for (Celula i = tmp; i != null; i = i.dir) {
				if(i.elemento == 2048) parar = true;
				if (i.elemento == 0) zero = true;

				if (i.elemento == 0 && num == 0){
					if(i.inf.inf.inf.elemento !=0) up = true;
					if(i.dir.dir.dir.elemento != 0) esq = true; 
				}
				if (i.elemento == 0 && num == 3){
					if(i.inf.inf.inf.elemento !=0) up = true;
					if(i.esq.esq.esq.elemento != 0) dir = true; 
				}

				if (i.elemento == 0 && num == 12){
					if(i.sup.sup.sup.elemento !=0) down = true;
					if(i.dir.dir.dir.elemento != 0) esq = true; 
				}

				if (i.elemento == 0 && num == 15){
					if(i.sup.sup.sup.elemento !=0) down = true;
					if(i.esq.esq.esq.elemento != 0) dir = true; 
				}
				num++;
			}
			tmp = j.inf;
		}

		if (zero == false) {
			MyIO.println("SEM 0");
			for (Celula j = inicio; j != null; j = j.inf) {
				for (Celula i = tmp; i != null; i = i.dir) {
					if (i.esq != null){
						if(i.esq.elemento == i.elemento)esq = true;
						MyIO.println(i.esq.elemento + " "+i.elemento);
					}
					if (i.sup != null){
						if(i.sup.elemento == i.elemento) up = true;
					}
					if (i.inf != null){
						if(i.inf.elemento == i.elemento) down = true;
					}
					if (i.dir != null){
						if(i.dir.elemento == i.elemento) dir = true;
					}
				}
				tmp = j.inf;
			}
		}

		if(parar == true) MyIO.print("NONE \n");
		else{
			if (down == true)
			MyIO.print("DOWN ");
		if (esq == true)
			MyIO.print("LEFT ");
		if (dir == true)
			MyIO.print("RIGHT ");
		if (up == true)
			MyIO.print("UP \n");
		if(zero == false && up != true && esq != true && dir != true && down != true)	
			MyIO.print("NONE \n");

			
		} 

		
	}
	
}

