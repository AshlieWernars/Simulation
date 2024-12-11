# Simulation

This is a human evolution system written by ChatGPT as an experiment. It simulates various human traits and behaviors, and models their interactions and reproduction based on these traits. The goal of this system is to simulate an evolving population with changing traits over time.

## Features

- **Human Traits**: Humans in the simulation possess various traits such as social skill, physical strength, intelligence, empathy, and more.
- **Behavior Types**: Humans exhibit different behavior types (e.g., aggressive, cooperative, friendly) that affect interactions with others.
- **Reproduction**: Humans reproduce based on trait compatibility, with the possibility of mutations in offspring.
- **Interactions**: Humans can interact with each other, which may impact their mental health, social skills, and physical traits.
- **Population Management**: The simulation allows for the population to grow and age, with a death rate increasing with age.

## Components

- **Human Class**: Represents an individual human with various traits and behaviors.
- **InteractionHandler Class**: Handles interactions and reproduction between humans. It contains methods for processing interactions based on human behavior and trait compatibility.
- **Behavior Class**: Defines different behavior types that humans can exhibit.
- **Population Management**: Manages the population of humans, including aging, reproduction, and death.

## How It Works

1. **Human Generation**: Each human is created with a set of traits (e.g., social skill, intelligence, physical strength).
2. **Interaction**: Humans interact with others, which can impact their mental health, social skills, and other traits.
3. **Reproduction**: Compatible humans can reproduce, producing offspring with traits derived from both parents. Mutations may occur.
4. **Population Dynamics**: The population grows and ages over time, with a chance for death based on age and health.

## Running the Simulation

1. Clone or download this repository.
2. Compile and run the project in your Java environment.
3. Observe the evolution of the human population over time based on interactions and reproduction.

## Future Improvements

- **More Detailed Traits**: Adding additional traits to simulate more complex human interactions.
- **Decision Making**: Improving decision-making logic for humans based on their traits and environmental factors.
- **Graphical Interface**: Adding a graphical interface to visualize human traits, interactions, and the population over time.

## License

This project is open-source and available under the [MIT License](LICENSE).
