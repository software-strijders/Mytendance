# Mytendance
Mytendance is een Presentie InformatieSysteem (PrIS). Dit systeem automatiseert de huidige werkwijze, waardoor er verschillende processen efficiënter zullen verlopen.

## Benodigheden
Dit project is geschreven in Java, met als UI-framework JavaFX. Dit project vereist een lokale installatie van het JavaFX framework.

### Git gerelateerd
Voordat je aan een user story/task begint moet je altijd even de laatste veranderingen ophalen (zonder $ natuurlijk):
```
$ git pull
```

#### Branches
Nadat je dit gedaan heb, is het handig om een branch aan te maken voor jouw specifieke story/task. Om alles geordend te houden maken we gebruik van een notatie.

Deze notatie is opgebouwd uit drie delen:
- feature/
- ID van de issue
- Korte beschrijving van user story/task

Voorbeeld:
```
$ git branch feature/1-add-login-screen 
```
Let op! Op dit moment heb je alleen maar een branch aangemaakt. dat wil zeggen dat je nog steeds op `master` zit. 

Om naar de nieuwe branch te gaan gebruik je de volgende commando:
```
$ git checkout feature/1-add-login-screen
```
Het kan zijn dat je een branch wil aanmaken en er tegelijkertijd naartoe gaan, dat kan:
```
$ git checkout -b feature/2-add-register-screen
```
de `-b` flag zorgt ervoor dat er gelijk een branche wordt aangemaakt.

#### Check de status
Het is handig om eerst te kijken wat voor bestanden er (bijvoorbeeld) aangemaakt zijn, dit kan je doen door het volgende:
```
$ git status
On branch feature/1-add-login-screen
Untracked files:
  (use "git add <file>..." to include in what will be committed)

        README.md

nothing added to commit but untracked files present (use "git add" to track)

```
In dit geval is `README.md` aangemaakt.
In het volgende voorbeeld kan je zien dat er iets is aangepast:
```
$ git status
On branch feature/1-add-login-screen
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

        modified:   README.md

no changes added to commit (use "git add" and/or "git commit -a")

```

#### Staging area
De [staging area](https://softwareengineering.stackexchange.com/a/119790) is een gebied waar de veranderingen (tijdelijk) opstaan. Hier kan je aangeven wat er in de volgende commit mee wordt genomen.

Om deze bestand(en) toe te voegen aan de staging area kan je het volgende gebruiken:
```
$ git add naam_van_bestand
```
Of:
```
$ git add naam_van_directory/
```

Voorbeeld van hoe het er in de staging area er uit ziet:
```
$ git status
On branch feature/1-add-login-screen
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

        new file:   README.md

```

#### Commits
Nadat je alles toegevoegd hebt aan de staging area, kan je het in een commit stoppen. Commits zijn lokale veranderingen die je in een soort van "moment" opslaat.

Om een commmit te maken kan je het volgende doen:
```
$ git commit
```
Dit opent waarschijnlijk `vim` of `nano` in je shell, dit kan je eventueel aanpassen naar een andere editor. Dit kan er zo uit zien:
```
COMMIT_MESSAGE_HIER
# Please enter the commit message for your changes. Lines starting
# with '#' will be ignored, and an empty message aborts the commit.
#
# On branch feature/1-add-login-screen
# Changes to be committed:
#	new file:   README.md
```
Dit toont een overzicht van alles wat je aan de staging area toegevoegd heb.

Vaak, voor kleine veranderingen, wil je het sneller doen. Dit kan door het volgende te doen:
```
$ git commit -m "Add README.md"
```
Zorg er wel voor dat je duidelijke commit messages gebruikt, deze zijn vaak in het engels. Kijk op <https://wiki.openstack.org/wiki/GitCommitMessages> voor good practices hiervoor.

Nadat je dit gemaakt heb, heb je de lokale verandering opgeslagen. Om alle commits terug te zien kan je (als je Git Bash gebruikt) de volgende commando uitvoeren:
```
$ tig
```
Dit laat een tekstuele weergave zien van al je commits (in de huidige branch!). Je kan hier ook andere tools voor gebruiken zoals:
- Git Kraken
- Sourcetree

Maar dat raad ik af, van de command line kan je meer leren!

#### Pushen
Nu we lokale veranderingen opgeslagen hebben in commits, kunnen we het op de remote repository zetten. In dit geval is dat GitHub zelf.

Waarschuwing: voordat je iets pushed, kijk altijd of je niet op de `master` branch zit!

Om te pushen moet je het volgende doen:
```
$ git push
```
Meestal, voor de eerste keer, weet Git nog niet waar die naartoe moet pushen. Als deze commando uitgevoerd wordt, krijg je het volgende te zien:
```
fatal: The current branch test has no upstream branch.
To push the current branch and set the remote as upstream, use

    git push --set-upstream origin test
```
Hier geeft Git al (slim) aan hoe je dit kan oplossen. Nadat je de voorgestelde commando hebt uitgevoerd, zal Git onthouden waarnaartoe die moet pushen

De volgende keren hoef je dus alleen maar `git push` te doen! 

Nadat je gepushed heb kan je een [Pull request](#pull-requests) maken.

#### Merge conflicts
Als er merge conflicts zijn, zal @xandervedder wel laten zien hoe je dat kan oplossen.

#### Overzicht meest gebruikte commando's
| Commando                                       | Beschrijving                                                                     |
| -----------------------------------------------|----------------------------------------------------------------------------------| 
| git pull                                       | Haalt de nieuwste verandering van remote (GitHub)                                | 
| git branch                                     | Toont alle branches (lokaal)                                                     |  
| git branch branch_naam                         | Maakt een nieuwe branch met naam                                                 |  
| git checkout branch_naam                       | gaat in de branch                                                                | 
| git checkout -b nieuwe_branch_naam             | Korte manier om een nieuwe branch aan te maken en gaat er gelijk in              | 
| git status                                     | Toont de status: modified, deleted en added changes                              | 
| git add naam_van_bestand                       | Voegt bestand toe aan staging                                                    | 
| git add naam_van_directory/                    | Voegt gehele directory aan staging (recursief, alle bestanden in directory)      | 
| git commit                                     | Opent een editor (`nano` of `vim`) om een commit message in te voeren            | 
| git commit -m "message"                        | Korte manier om een commit aan te maken met message                              | 
| git push                                       | Pushed gemaakte commits naar remote (GitHub). Check of je niet in master zit!    | 
| git push --set-upstream origin naam_van_branch | Hierdoor weet Git waarnaartoe die moet pushen                                    | 
| tig                                            | Toont een tekstuele representatie van de geschiedenis van commits                | 

#### Ik kom er niet uit
Het kan gebeuren dat je niet meer weet wat je moet doen of je hebt iets gesloopt. Als je problemen heb met Git, vraag @xandervedder om hulp!

### Pull requests
Nadat je hebt gepushed, moet je naar GitHub gaan. In GitHub zie je bovenaan het scherm de branch waarnaartoe je gepushed hebt. GitHub vraagt dan ook of je dan een Pull request wil maken. Dit zou ik ook gaan doen.

Voor elke Pull request **moeten** minstens twee personen gereviewed hebben. We doen dit om het niveau van code kwaliteit zo hoog mogelijk te houden. 

In het Pull request scherm kan je reviewers aan de Pull request toevoegen. De volgende mensen zijn reviewers:
- @xandervedder
- @joeri-hu
- @JortWillemsen

De reviewers kunnen vragen of je bepaalde dingen kan aanpassen, hier kan je veel van leren!

Nadat de twee personen je Pull request goedkeuren, kan er gemerged worden. 
Let op: @xandervedder is de enige die (onofficieel) mag mergen! Hou je hier ook aan a.u.b.

### Issues
Dit gebruiken we vooral om de user stories uit te laten breiden. Je kan bijvoorbeeld subtaken toevoegen, hierdoor kan je precies zien wat er van je verwacht wordt.

#### Hoe maak ik deze subtaken?
Doormiddel van [markdown](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet) kunnen we gemakkelijk allerlei opmaak toevoegen aan een Issue (maar ook Pull requests!).

Om een subtaak te maken moet je het volgende doen:
```
- [ ] taak 1
- [X] taak 2 (klaar)
```
- [ ] taak 1
- [X] taak 2 (klaar)
