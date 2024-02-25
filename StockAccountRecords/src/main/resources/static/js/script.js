function changeTab(tab) {
    if (tab === 'login') {
        document.getElementById('form-title').innerText = 'Login';
        document.getElementById('login-form').style.display = 'block';
        document.getElementById('register-form').style.display = 'none';
        document.getElementById('login-tab').classList.add('active');
        document.getElementById('register-tab').classList.remove('active');
    } else if (tab === 'register') {
        document.getElementById('form-title').innerText = 'Register';
        document.getElementById('login-form').style.display = 'none';
        document.getElementById('register-form').style.display = 'block';
        document.getElementById('login-tab').classList.remove('active');
        document.getElementById('register-tab').classList.add('active');
    }
}

document.addEventListener("DOMContentLoaded", function () {
    document.getElementById('depositInput').addEventListener('input', updateBalance);
    document.getElementById('spendInput').addEventListener('input', updateBalance);
});

function updateBalance() {
    var depositValue = parseFloat(document.getElementById('depositInput').value) || 0;
    var spendValue = parseFloat(document.getElementById('spendInput').value) || 0;

    var lastBalanceCell = document.querySelector(".transaction-table tbody tr:last-child td:nth-child(4)");
    var lastBalance = lastBalanceCell ? parseFloat(lastBalanceCell.textContent) || 0 : 0;

    var newBalance = lastBalance + depositValue - spendValue;

    document.getElementById('balanceInput').value = newBalance;
}

function addNewTransaction() {
    var modal = new bootstrap.Modal(document.getElementById('addTransactionModal'));

    modal.show();
}

function createInput(type, placeholder, id) {
    var input = document.createElement("input");
    input.type = type;
    input.placeholder = placeholder;
    input.id = id;
    return input;
}

function handleFormSubmission() {
    // Get values from input fields
    var dateValue = document.getElementById('dateInput').value;
    var depositValue = document.getElementById('depositInput').value;
    var spendValue = document.getElementById('spendInput').value;
    var balanceValue = document.getElementById('balanceInput').value;
    var remarkValue = document.getElementById('remarkInput').value;
    var usernameValue = document.getElementById('usernameInput').value;

    if (dateValue && balanceValue !== null) {
        // AJAX request to send data to the backend
        $.ajax({
            type: 'POST',
            url: '/api/addTransaction', // Adjust the URL based on your backend endpoint
            contentType: 'application/json',
            data: JSON.stringify({
                username: usernameValue,
                date: dateValue,
                deposit: depositValue,
                spend: spendValue,
                balance: balanceValue,
                remark: remarkValue
            }),
            success: function (response) {
                updateUI({
                            date: dateValue,
                            deposit: depositValue,
                            spend: spendValue,
                            balance: balanceValue,
                            remark: remarkValue
                        });
            },
            error: function (error) {
                console.error('Error:', error);
            }
        });
    } else {
        // Show an alert for incomplete input
        alert("Please fill in all required fields.");
    }
}

function updateUI(response) {
    var newRow = document.createElement("tr");

    var dateCell = document.createElement("td");
    dateCell.textContent = response.date;
    newRow.appendChild(dateCell);

    var depositCell = document.createElement("td");
    depositCell.textContent = response.deposit;
    newRow.appendChild(depositCell);

    var spendCell = document.createElement("td");
    spendCell.textContent = response.spend;
    newRow.appendChild(spendCell);

    var balanceCell = document.createElement("td");
    balanceCell.textContent = response.balance;
    newRow.appendChild(balanceCell);

    var remarkCell = document.createElement("td");
    remarkCell.textContent = response.remark;
    newRow.appendChild(remarkCell);

    var tableBody = document.querySelector(".transaction-table tbody");
    tableBody.appendChild(newRow);

    var modal = bootstrap.Modal.getInstance(document.getElementById('addTransactionModal'));
    modal.hide();
}
