package ticketmachine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de
	// l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() {
		// GIVEN : une machine vierge (initialisée dans @BeforeEach)
		// WHEN On insère de l'argent
		machine.insertMoney(10);
		machine.insertMoney(20);
		// THEN La balance est mise à jour, les montants sont correctement additionnés
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");
	}

	@Test
	//S3
	void notPrintNotSuffisant(){
		assertFalse(machine.printTicket(), "Le montant n'est pas suffisant pour imprimer le ticket");
	}

	@Test
	//S4
	void PrintIfSuffisant(){
		machine.insertMoney(50);
		assertTrue(machine.printTicket(), "Le montant est suffisant pour imprimer le ticket");
	}

	@Test//S5
	void decrementation(){
		machine.insertMoney(60);
		machine.printTicket();
		assertEquals(60-PRICE, machine.getBalance(), "La balance n'est pas correctement mise à jour après impression du ticket");
	}

	@Test //S6
	void Majcollecte(){
		machine.insertMoney(60);
		machine.printTicket();
		assertEquals(50, machine.getTotal(), "erreur");
	}

	@Test //S7
	void verifRefund(){
		machine.insertMoney(60);
		machine.printTicket();
		assertEquals(10, machine.refund(), "erreur");
	}

	@Test //S8
	void verifRefund0(){
		machine.insertMoney(60);
		machine.printTicket();
		machine.refund();
		assertEquals(0, machine.getBalance(), "erreur");
	}

	@Test //S9
	void verifMontantpositif(){
		assertTrue(machine.insertMoney(60), "erreur");
	}

	@Test //S10
	void verifTicketpositif(){
		machine.insertMoney(60);
		assertTrue(machine.printTicket(), "erreur");
	}

}
