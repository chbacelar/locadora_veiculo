# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Java-based vehicle rental management system (Sistema de Locadora de Veículos) with a Swing GUI interface. The application manages a fleet of vehicles (cars and motorcycles) with rental operations.

## Build and Run Commands

This is a plain Java project without build automation tools. Compile and run using:

```bash
# Compile all Java files
javac -d bin src/com/locadora/**/*.java src/com/locadora/*.java

# Run the application
java -cp bin com.locadora.Main
```

Alternative using direct compilation:
```bash
# Compile from src directory
javac src/com/locadora/**/*.java src/com/locadora/*.java

# Run (adjust classpath based on where .class files are generated)
java com.locadora.Main
```

## Architecture

### Package Structure
- `com.locadora` - Main application entry point
- `com.locadora.model` - Domain models (abstract `Veiculo` and concrete `Carro`, `Moto`)
- `com.locadora.manager` - Business logic layer (`LocadoraManager`)

### Core Design Patterns

**Model Hierarchy**: Abstract base class pattern with polymorphism
- `Veiculo` (abstract base) - Common vehicle properties (placa, marca, modelo, ano, valorDiaria, disponivel)
- `Carro` extends `Veiculo` - Adds numeroPortas, tipoCombustivel
- `Moto` extends `Veiculo` - Adds cilindradas, tipoPartida
- Abstract method `getTipo()` implemented by subclasses for type identification

**Manager Pattern**: `LocadoraManager` provides centralized CRUD and business operations
- In-memory storage using `ArrayList<Veiculo>`
- Operations: adicionarVeiculo, removerVeiculo, buscarPorPlaca, alugarVeiculo, devolverVeiculo
- Validation: Prevents duplicate placas, ensures availability checks for rentals
- No persistence layer - data exists only in memory during runtime

**UI Layer**: `Main` class handles all user interaction through JOptionPane dialogs
- Menu-driven interface with 9 operations
- Direct coupling to `LocadoraManager` (static instance)
- Input validation with try-catch blocks for NumberFormatException

### Key Implementation Details

**Vehicle Identification**: Placa (license plate) is the unique identifier
- Case-insensitive search via `equalsIgnoreCase()`
- Duplicate placa validation throws `IllegalArgumentException`

**Rental State Management**: Boolean `disponivel` flag
- `true` = available for rent, `false` = currently rented
- All vehicles default to available on creation
- State transitions handled through manager methods

**No External Dependencies**: Pure Java SE with Swing
- No framework dependencies
- No database or file persistence
- No build tool configuration files

## Common Operations

When adding new vehicle types:
1. Extend `Veiculo` abstract class
2. Implement `getTipo()` method
3. Override `exibirInformacoes()` to include subclass-specific fields
4. Update `Main.cadastrarVeiculo()` to handle the new type in the switch statement

When modifying business logic:
- All operations go through `LocadoraManager` - never manipulate the vehicles list directly from UI
- Maintain the placa uniqueness constraint
- Preserve the availability state machine (disponivel flag transitions)
