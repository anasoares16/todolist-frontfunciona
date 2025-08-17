// Seleciona os elementos
const input = document.querySelector("input[type='text']");
const addBtn = document.querySelector("button");
const taskList = document.querySelector("ul");

// Função para adicionar tarefa
function addTask() {
    const taskText = input.value.trim();

    if (taskText === "") {
        alert("Digite uma tarefa!");
        return;
    }

    // Criar <li>
    const li = document.createElement("li");
    li.textContent = taskText;

    // Botão de excluir
    const deleteBtn = document.createElement("button");
    deleteBtn.textContent = "✓"; 
    deleteBtn.classList.add("delete-btn");

    // Ações
    deleteBtn.addEventListener("click", () => {
        li.remove();
    });

    li.addEventListener("click", () => {
        li.classList.toggle("completed");
    });

    // Monta item
    li.appendChild(deleteBtn);
    taskList.appendChild(li);

    // Limpa input
    input.value = "";
}

// Evento do botão
addBtn.addEventListener("click", addTask);

// Enter para adicionar
input.addEventListener("keypress", (e) => {
    if (e.key === "Enter") {
        addTask();
    }
});
