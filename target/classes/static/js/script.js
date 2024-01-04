// script.js

function showCreateForm() {
    // Oculta outros formulários, se houver
    hideAllForms();
    
    // Exibe o formulário de criação
    document.getElementById('createForm').style.display = 'block';
  }
  
  function hideAllForms() {
    // Oculta todos os formulários
    var forms = document.querySelectorAll('.container form');
    forms.forEach(function(form) {
      form.style.display = 'none';
    });
  }
  
  function submitForm() {
    // Adicione lógica para enviar o formulário, se necessário
    alert('Formulário enviado! Implemente a lógica de envio aqui.');
  }
  

  function submitForm() {
    // Captura dos valores do formulário
    const tipo = document.getElementById("tipo").value;
    const quantBolas = parseInt(document.getElementById("quantBolas").value);
    const peso = parseFloat(document.getElementById("peso").value);
    const descricao = document.getElementById("descricao").value;
    const valor = parseFloat(document.getElementById("valor").value);

    // Criação do objeto JSON
    const formData = {
        tipo: tipo,
        quantBolas: quantBolas,
        peso: peso,
        descricao: descricao,
        valor: valor
    };

    // Requisição POST
    fetch('http://localhost:8080/tipoSorvete', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        // Adicione aqui o código para tratar a resposta do servidor, se necessário
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

function showCreateForm() {
    document.getElementById("createForm").style.display = "block";
}