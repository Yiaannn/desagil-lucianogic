package br.pro.hashi.ensino.desagil.lucianogic.model;

public class HalfGate extends Gate {
	private XorGate xor;
	private AndGate and;
	private MuxGate mux;
	private Switch selector;

	public HalfGate() {
		super(2, 2);

		name = "HALF";

		xor= new XorGate();
        and= new AndGate();
        
        mux= new MuxGate();
        mux.connect(xor, 0);
        mux.connect(and, 1);
        selector= new Switch();
        mux.connect(selector, 2);
	}

	@Override
	public boolean doRead(int index) {
	    selector.setOn(index!=0);
		return mux.read();
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		xor.connect(emitter, index);
		and.connect(emitter, index);
	}
}
