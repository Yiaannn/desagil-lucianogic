package br.pro.hashi.ensino.desagil.lucianogic.model;

public class FullGate extends Gate {
	private XorGate xor_a;
	private XorGate xor_b;
	private AndGate and_a;
	private AndGate and_b;
	private OrGate or;
	private MuxGate mux;
	private Switch selector;

	public FullGate() {
		super(3, 2);

		name = "FULL";

		xor_a= new XorGate();
        and_b= new AndGate();
        
        xor_b= new XorGate();
        xor_b.connect(xor_a, 0);
        
        and_a= new AndGate();
        and_a.connect(xor_a, 0);
        
        or= new OrGate();
        or.connect(and_a, 0);
        or.connect(and_b, 1);
        
        mux= new MuxGate();
        mux.connect(xor_b, 0);
        mux.connect(or, 1);
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
		switch(index) {
		case 0:
			xor_a.connect(emitter, 0);
			and_b.connect(emitter, 0);
			break;
		case 1:
			xor_a.connect(emitter, 1);
			and_b.connect(emitter, 1);
			break;
		case 2:
			xor_b.connect(emitter, 1);
			and_a.connect(emitter, 1);
			break;
		}
	}
}
