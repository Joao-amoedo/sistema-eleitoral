package pi.model;

public enum UF {
	TO((short) 0), MA((short) 1), PA((short) 2);

	private short codigo;

	UF(short codigo) {
		this.codigo = codigo;
	}

	public short getCodigo() {
		return this.codigo;
	}

	public static UF getUF(short codigo) {
		UF[] values = UF.values();
		for (UF uf : values) {
			if(uf.getCodigo() == codigo)
				return uf;
		}
		return null;
	}
}
