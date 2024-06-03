# Makefile 

# Aim
.PHONY: all
all: compile run

# Path 
SRC_DIR := .
BIN_DIR := bin

# Java Files
SRC_FILES := $(wildcard $(SRC_DIR)/*.java)

# Classes
CLASS_FILES := $(patsubst $(SRC_DIR)/%.java, $(BIN_DIR)/%.class, $(SRC_FILES))

# Running file name
MAIN_CLASS := Main

INPUT_FILE := input.txt

# Compile
compile: $(CLASS_FILES)

$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	mkdir -p $(BIN_DIR)
	javac -d $(BIN_DIR) $<

# Run
run:
	java -cp $(BIN_DIR) $(MAIN_CLASS) "$(INPUT_FILE)"

# Clean
.PHONY: clean
clean:
	rm -rf $(BIN_DIR)
